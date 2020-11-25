import React, { Component } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

//Componentes
import { Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import { NotificationContainer, NotificationManager } from 'react-notifications';
import Button from '@material-ui/core/Button';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';

//Estilos
import 'react-notifications/lib/notifications.css';

//Iconos
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import CloseIcon from '@material-ui/icons/Close';
import AssignmentTurnedInIcon from '@material-ui/icons/AssignmentTurnedIn';
import CancelIcon from '@material-ui/icons/Cancel';
import RestoreFromTrashIcon from '@material-ui/icons/RestoreFromTrash';

const urlBase = 'http://localhost:9090/ProyectoServiciosDocente-web';

//Constructor
class Materia extends Component {
	constructor(props) {
		super(props);

		this.state = {		
			lista: [],
			modalInsertar: false,
			modalEliminar: false,
			tipoModal: '',
			form: {
				id: '',
				nombre: ''
			}
		};

		this.cambiarEstadoModal = this.cambiarEstadoModal.bind(this);
		this.cambiarEstadoModalEliminar = this.cambiarEstadoModalEliminar.bind(this);
		this.registrar = this.registrar.bind(this);
		this.editar = this.editar.bind(this);
		this.eliminar = this.eliminar.bind(this);
	}

	componentDidMount() {
		this.listar();
	}

	//Servicios

	registrar() {
		delete this.state.form.id;
		axios
			.post(`${urlBase}/api/materias/registrar`, this.state.form)
			.then((response) => {
				this.cambiarEstadoModal();
				this.listar();
				NotificationManager.success(response.data);
			})
			.catch((error) => {
				console.log(error.response.data);
				NotificationManager.error(error.response.data.error);
			});
	}

	listar() {
		axios
			.get(`${urlBase}/api/materias/listar`)
			.then((response) => {
				console.log(response.data);
				this.setState({
					lista: response.data
				});
			})
			.catch((error) => {
				NotificationManager.error(error.response.data.error);
			});
	}

	editar() {
		console.log(this.state.form);
		axios
			.put(`${urlBase}/api/materias/editar`, this.state.form)
			.then((response) => {
				this.cambiarEstadoModal();
				this.listar();
				NotificationManager.success(response.data);
			})
			.catch((error) => {
				//error.response.data es lo que arrojo el servidor en caso de error
				console.log(error.response.data);
				NotificationManager.error(error.response.data.error);
			});
	}

	eliminar() {
		console.log(this.state.form);
		axios
			.delete(`${urlBase}/api/materias/eliminar/${this.state.form.id}`)
			.then((response) => {
				this.setState({ modalEliminar: false });
				this.listar();
				NotificationManager.success('Eliminada correctamente');
			})
			.catch((error) => {
				//error.response.data es lo que arrojo el servidor en caso de error
				console.log(error.response.data);
				NotificationManager.error(error.response.data.error);
			});
	}

	//Modal
	modalInsertar = () => {
		this.setState({ modalInsertar: !this.state.modalInsertar });
	};

	cambiarEstadoModal() {
		this.setState({ modalInsertar: !this.state.modalInsertar });
	}

	cambiarEstadoModalEliminar() {
		this.setState({ modalEliminar: !this.state.modalEliminar });
	}

	//Obtener materia tabla
	seleccionarMateria = (materia) => {
		this.setState({
			tipoModal: 'actualizar',
			form: {
				id: materia.id,
				nombre: materia.nombre
			}
		});
	};

	//Capturar los datos input
	handleChange = async (e) => {
		e.persist();
		await this.setState({
			form: {
				...this.state.form,
				[e.target.name]: e.target.value
			}
		});
	};

	render() {
		const { form } = this.state;
		return (
			<div className="container">
				<div className="row">
					<div className="col-2" />
					<div className="col-4">
						<Button
							style={{
								background: 'rgb(58, 183, 17)',
								fontSize: '13px',
								fontFamily: 'sans-serif',
								textTransform: 'none'
							}}
							className="btn btn-dark"
							variant="contained"
							startIcon={<AddCircleOutlineIcon />}
							type="submit"
							onClick={() => {
								this.setState({ form : {}, tipoModal: 'insertar' });
								this.modalInsertar();
							}}
						>
							Agregar Materia
						</Button>
						{''}
					</div>

					<div className="col-6" />

					<div className="col-12 m-3"></div>

					<div className="col-2" />
					<div className="col-8">
						<TableContainer
							style={{
								background: 'rgb(245,245,245)',
								position: 'relative',
								overflow: 'auto',
								maxHeight: 350
							}}
						>
							<Table aria-label="simple table" size="small">
								<TableHead>
									<TableRow style={{ background: 'rgb(200,200,200)' }}>
										<TableCell style={{ fontWeight: 'bold' }}>Nombre</TableCell>
										<TableCell style={{ fontWeight: 'bold' }}>Acciones</TableCell>
									</TableRow>
								</TableHead>
								<TableBody>
									{[ ...this.state.lista ].map((materia) => {
										return (
											<TableRow key={materia.id}>
												<TableCell>{materia.nombre}</TableCell>
												<TableCell>
													<IconButton
														onClick={() => {
															this.seleccionarMateria(materia);
															this.modalInsertar();
														}}
													>
														<EditIcon color="primary" />
													</IconButton>

													<IconButton
														onClick={() => {
															this.seleccionarMateria(materia);
															this.setState({ modalEliminar: true });
														}}
													>
														<DeleteIcon color="error" />
													</IconButton>
												</TableCell>
											</TableRow>
										);
									})}
								</TableBody>
							</Table>
						</TableContainer>
					</div>
					<div className="col-2" />
				</div>

				<Modal isOpen={this.state.modalInsertar}>
					<ModalHeader style={{ display: 'block' }}>
						{this.state.tipoModal === 'insertar' ? 'Registrar Materia' : 'Editar Materia'}
						
						<IconButton style={{ float: 'right' }} onClick={this.modalInsertar}>
							<CloseIcon color="inherit"></CloseIcon>
						</IconButton>
					</ModalHeader>
					<ModalBody>
						<div className="form-group">
							<label htmlFor="id">Id</label>
							<input
								className="form-control"
								type="number"
								name="id"
								id="id"
								readOnly
								onChange={this.handleChange}
								value={form.id ? form.id : 0}
							/>
							<br />
							<label htmlFor="nombre">Nombre</label>
							<input
								className="form-control"
								type="text"
								name="nombre"
								id="nombre"
								required="true"
								onChange={this.handleChange}
								value={form.nombre ? form.nombre : ''}
							/>
						</div>
					</ModalBody>

					<ModalFooter>
					{this.state.tipoModal === 'insertar' ?
							<Button style={{ background: "rgb(58, 183, 17)", fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark mr-2" variant="contained" startIcon={<AssignmentTurnedInIcon />} type="submit" onClick={this.registrar}>Insertar</Button> :
							<Button style={{ background: "rgb(58, 183, 17)", fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark mr-2" variant="contained" startIcon={<EditIcon />} type="submit" onClick={this.editar}>Editar</Button>
						}
						<Button style={{ background: "gray", fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark ml-2" variant="contained" startIcon={<CancelIcon />} type="submit" onClick={this.modalInsertar}>Cancelar</Button>{''}
					</ModalFooter>
				</Modal>

				<Modal isOpen={this.state.modalEliminar}>
					<ModalHeader style={{ display: 'block' }}>
						Confirmación
						
						<IconButton style={{ float: 'right' }} onClick={this.cambiarEstadoModalEliminar}>
							<CloseIcon color="inherit"></CloseIcon>
						</IconButton>
					</ModalHeader>
					<ModalBody>
						Estás seguro de eliminar la materia {this.state.form && this.state.form.nombre}
					</ModalBody>
					<ModalFooter>
						<Button style={{ background: "red", fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark mr-2" variant="contained" startIcon={<RestoreFromTrashIcon />} type="submit" onClick={this.eliminar}>Eliminar</Button>
                        <Button style={{ background: "gray", fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark ml-2" variant="contained" startIcon={<CancelIcon />} type="submit" onClick={this.cambiarEstadoModalEliminar}>Cancelar</Button>{''}
					</ModalFooter>
				</Modal>
				<NotificationContainer />
			</div>
		);
	}
}

export default Materia;
