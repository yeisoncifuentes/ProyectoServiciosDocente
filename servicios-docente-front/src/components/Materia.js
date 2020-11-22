import React, { Component } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

//Componentes
import { Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import Button from '@material-ui/core/Button';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';

//Iconos
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import IconButton from '@material-ui/core/IconButton';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';

const urlBase = 'http://localhost:9090/ProyectoServiciosDocente-web';

class Materia extends Component {
	constructor(props) {
		super(props);

		this.state = {
			lista: [],
			modalInsertar: false,
			modalEliminar: false,
			form: {
				id: '',
				nombre: ''
			}
		};
	}

	modalInsertar = () => {
		this.setState({ modalInsertar: !this.state.modalInsertar });
	};

	seleccionarMateria = (materia) => {
		this.setState({
			tipoModal: 'actualizar',
			form: {
				id: materia.id,
				nombre: materia.nombre
			}
		});
	};

	handleChange = async (e) => {
		e.persist();
		await this.setState({
			form: {
				...this.state.form,
				[e.target.name]: e.target.value
			}
		});
		console.log(this.state.form);
	};

	componentDidMount() {
		this.listar();
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
				console.log(error);
			});
	}

	

	render() {
		return (
			<div className="container">
				<div className="row">
					<div className="col-2" />
					<div className="col-4">
						<Button
							style={{
								background: 'rgb(235, 126, 21)',
								fontSize: '13px',
								fontFamily: 'sans-serif',
								textTransform: 'none'
							}}
							className="btn btn-dark"
							variant="contained"
							startIcon={<AddCircleOutlineIcon />}
							type="submit"
							onClick={() => {
								this.setState({ form: null, tipoModal: 'insertar' });
								this.modalInsertar();
							}}
						>
							Agregar Materia
						</Button>
						{''}
					</div>

					<div className="col-6" />

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
															this.setState({ modalEliminar: true });
														}}
													>
														<DeleteIcon color="secondary" />
													</IconButton>
													<IconButton
														onClick={() => {
															this.seleccionarMateria(materia);
															this.modalInsertar();
														}}
													>
														<EditIcon color="primary" />
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
						<span style={{ float: 'right' }} onClick={() => this.modalInsertar()}>
							x
						</span>
					</ModalHeader>
					<ModalBody>
						<div className="form-group">
							<label htmlFor="id">ID</label>
							<input
								className="form-control"
								type="text"
								name="id"
								id="id"
								readOnly
								onChange={this.handleChange}
								value={this.state.form ? this.state.form.id : this.state.lista.length + 1}
							/>
							<br />
							<label htmlFor="nombre">Nombre</label>
							<input
								className="form-control"
								type="text"
								name="nombre"
								id="nombre"
								onChange={this.handleChange}
								value={this.state.form ? this.state.form.nombre : ''}
							/>
						</div>
					</ModalBody>

					<ModalFooter>
						{this.state.tipoModal == 'insertar' ? (
							<button className="btn btn-success">
								Insertar
							</button>
						) : (
							<button className="btn btn-primary">
								Actualizar
							</button>
						)}
						<button className="btn btn-danger" onClick={() => this.modalInsertar()}>
							Cancelar
						</button>
					</ModalFooter>
				</Modal>

				<Modal isOpen={this.state.modalEliminar}>
					<ModalBody>
						Estás seguro de eliminar la materia {this.state.form && this.state.form.nombre}
					</ModalBody>
					<ModalFooter>
						<button className="btn btn-danger" >
							Sí
						</button>
						<button className="btn btn-secundary" onClick={() => this.setState({ modalEliminar: false })}>
							No
						</button>
					</ModalFooter>
				</Modal>
			</div>
		);
	}
}

export default Materia;
