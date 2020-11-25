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
class Estudiante extends Component {
	constructor(props) {
		super(props);

		this.state = {
			idDocente: '',
			lista: [],
			listaDocente: [],
			modalInsertar: false,
			modalEliminar: false,
			tipoModal: '',
			form: {
				id: '',
				nombre: '',
				apellido: '',
				docente: {
					id: '',
					cedula: ''
				}
			}
		};

		this.cambiarEstadoModal = this.cambiarEstadoModal.bind(this);
		this.cambiarEstadoModalEliminar = this.cambiarEstadoModalEliminar.bind(this);
		this.registrar = this.registrar.bind(this);
		this.editar = this.editar.bind(this);
		this.eliminar = this.eliminar.bind(this);
		this.handleChanger = this.handleChanger.bind(this);
	}

	componentDidMount() {
		this.listar();
		this.listarDocentes();
	}

	//Servicios

	registrar() {
		delete this.state.form.id;
		this.state.form.docente.id = this.state.idDocente;
		console.log(this.state.form);
		axios
			.post(`${urlBase}/api/estudiantes/registrar`, this.state.form)
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
			.get(`${urlBase}/api/estudiantes/listar`)
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

	listarDocentes() {
		axios
			.get(`${urlBase}/api/docentes/listar/false`)
			.then((response) => {
				this.setState({
					listaDocente: response.data
				});
			})
			.catch((error) => {
				NotificationManager.error(error.response.data.error);
			});
	}

	editar() {
		console.log(this.state.form);

		axios
			.put(`${urlBase}/api/estudiantes/editar`, this.state.form)
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
			.delete(`${urlBase}/api/estudiantes/eliminar/${this.state.form.id}`)
			.then((response) => {
				this.setState({ modalEliminar: false });
				this.listar();
				NotificationManager.success('Eliminado correctamente');
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

	//Obtener estudiante tabla
	seleccionarEstudiante = (estudiante) => {
		this.setState({
			tipoModal: 'actualizar',
			form: {
				id: estudiante.id,
				nombre: estudiante.nombre,
				apellido: estudiante.apellido,
				docente: {
					id: estudiante.docente.id,
					cedula: estudiante.docente.cedula,
					nombreDocente: estudiante.docente.nombre
				}
			}
		});
		console.log(this.state.form);
	};

	//Capturar los datos input
	handleChange = async (e) => {
		if (e.target.name === 'id') {
			await this.setState({
				form: {
					...this.state.form,
					docente: {
						...this.state.form.docente,
						[e.target.name]: e.target.value
					}
				}
			});
		} else {
			await this.setState({
				form: {
					...this.state.form,
					[e.target.name]: e.target.value
				}
			});
		}
	};
	//Captura el docente seleccionado
	handleChanger(event) {
		//console.log(`Seleccionaste ${event.target.value}`);
		this.state.idDocente = event.target.value;
		this.state.form.docente.id = this.state.idDocente;
		//console.log(this.state.form.docente.id);
	}

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
								this.setState({ form: { docente: {} }, idDocente: null, tipoModal: 'insertar' });
								this.modalInsertar();
							}}
						>
							Agregar Estudiante
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
										<TableCell style={{ fontWeight: 'bold' }}>Apellido</TableCell>
										<TableCell style={{ fontWeight: 'bold' }}>Docente</TableCell>
										<TableCell style={{ fontWeight: 'bold' }}>Acciones</TableCell>
									</TableRow>
								</TableHead>
								<TableBody>
									{[...this.state.lista].map((estudiante) => {
										return (
											<TableRow key={estudiante.id}>
												<TableCell>{estudiante.nombre}</TableCell>
												<TableCell>{estudiante.apellido}</TableCell>
												<TableCell>
													{estudiante.docente.nombre + ' ' + estudiante.docente.apellido}
												</TableCell>
												<TableCell>
													<IconButton
														onClick={() => {
															this.seleccionarEstudiante(estudiante);
															this.modalInsertar();
														}}
													>
														<EditIcon color="primary" />
													</IconButton>

													<IconButton
														onClick={() => {
															this.seleccionarEstudiante(estudiante);
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
						{this.state.tipoModal == 'insertar' ? 'Registrar Estudiante' : 'Editar Estudiante'}

						<IconButton style={{ float: 'right' }} onClick={this.modalInsertar}>
							<CloseIcon color="inherit"></CloseIcon>
						</IconButton>
					</ModalHeader>
					<ModalBody>
						<div className="form-group">
							<label htmlFor="idEstudiante">Id</label>
							<input
								className="form-control"
								type="number"
								name="idEstudiante"
								id="idEstudiante"
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
								required
								onChange={this.handleChange}
								value={form.nombre ? form.nombre : ''}
							/>
							<br />
							<label htmlFor="apellido">Apellido</label>
							<input
								className="form-control"
								type="text"
								name="apellido"
								id="apellido"
								required
								onChange={this.handleChange}
								value={form.apellido ? form.apellido : ''}
							/>
							<br />
							<label htmlFor="Docente">Docente </label>
							<select class="form-control" onChange={this.handleChanger}>
								{this.state.tipoModal == 'insertar' ? <option>Seleccione</option> : false}

								{this.state.listaDocente.map((docente) => (
									<option
										value={docente.id}
										selected={
											docente.nombre == this.state.form.docente.nombreDocente ? true : false
										}
									>
										{docente.nombre + ' ' + docente.apellido}
									</option>
								))}
							</select>
						</div>
					</ModalBody>

					<ModalFooter>
						{this.state.tipoModal == 'insertar' ?
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
						¿Estás seguro de eliminar al Estudiante? {this.state.form && this.state.form.nombre}
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

export default Estudiante;
