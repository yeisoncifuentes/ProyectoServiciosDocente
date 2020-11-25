import React, { Component } from 'react';
import axios from 'axios';

//Estilos 
import 'bootstrap/dist/css/bootstrap.min.css';
import "react-notifications/lib/notifications.css";

//Componentes
import { Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import { NotificationContainer, NotificationManager } from 'react-notifications';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";

//Iconos
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';
import AssignmentTurnedInIcon from '@material-ui/icons/AssignmentTurnedIn';
import RestoreFromTrashIcon from '@material-ui/icons/RestoreFromTrash';
import CancelIcon from '@material-ui/icons/Cancel';
import CloseIcon from '@material-ui/icons/Close';
import MenuBookIcon from '@material-ui/icons/MenuBook';

const urlBase = 'http://localhost:9090/ProyectoServiciosDocente-web';


class Docente extends Component {

    constructor(props) {
        super(props);

        this.state = {
            listaDocentes: [],
            modalFormulario: false,
            modalEliminar: false,
            modalMaterias: false,
            tipoModal: '',
            formulario: {
                id: '',
                cedula: '',
                nombre: '',
                apellido: '',
                correo: '',
                fechaNacimiento: new Date().setMonth(new Date().getMonth() - 240),
                direccion: {
                    barrio: '',
                    direccion: ''
                }
            },
            idDocente: '',
            listaMateriasNoAsignadas: [],
            listaMateriasAsignadas: []
        }

        this.solicitarInsercion = this.solicitarInsercion.bind(this);
        this.cambiarEstadoModalFormulario = this.cambiarEstadoModalFormulario.bind(this);
        this.cambiarEstadoModalEliminar = this.cambiarEstadoModalEliminar.bind(this);
        this.cambiarEstadoModalMaterias = this.cambiarEstadoModalMaterias.bind(this);
        this.capturarFecha = this.capturarFecha.bind(this);

        this.registrarDocente = this.registrarDocente.bind(this);
        this.editarDocente = this.editarDocente.bind(this);
        this.eliminarDocente = this.eliminarDocente.bind(this);
    }

    componentDidMount() {
        this.listarDocentes();
    }

    //Servicios
    registrarDocente() {
        //delete this.state.formulario.id;
        axios.post(`${urlBase}/api/docentes/registrar`, this.state.formulario)
            .then(response => {
                this.cambiarEstadoModalFormulario();
                this.listarDocentes();
                NotificationManager.success(response.data);
            }).catch((error) => {
                //error.response.data es lo que arrojo el servidor en caso de error
                console.log(error.response.data);
                NotificationManager.error(error.response.data.error);
            });
    }

    listarDocentes() {
        axios.get(`${urlBase}/api/docentes/listar/false`)
            .then(response => {
                this.setState({
                    listaDocentes: response.data
                });
            }).catch((error) => {
                console.log(error.response.data);
            });
    }

    editarDocente() {
        let fecha = new Date(this.state.formulario.fechaNacimiento);
        let docente = this.state.formulario;
        docente.fechaNacimiento = fecha;
        //Al parecer 'docente' accede a 'formulario'
        axios.put(`${urlBase}/api/docentes/editar`, this.state.formulario)
            .then(response => {
                this.cambiarEstadoModalFormulario();
                this.listarDocentes();
                NotificationManager.success(response.data);
            }).catch((error) => {
                //error.response.data es lo que arrojo el servidor en caso de error
                console.log(error.response.data);
                NotificationManager.error(error.response.data.error);
            });
    }

    eliminarDocente() {
        axios.delete(`${urlBase}/api/docentes/eliminar/${this.state.formulario.id}`)
            .then(response => {
                this.setState({ modalEliminar: false });
                this.listarDocentes();
                NotificationManager.success("Docente eliminado correctamente");
            }).catch((error) => {
                //error.response.data es lo que arrojo el servidor en caso de error
                console.log(error.response.data);
                NotificationManager.error(error.response.data.error);
            });
    }

    asociarDocenteMateria(idMateria) {
        let idDocente = this.state.idDocente;
        let DocenteMateria = {
            docente: {id: idDocente},
            materia: {id: idMateria}
        }
        axios.post(`${urlBase}/api/docentes/asociarDocente`, DocenteMateria)
            .then(response => {
                this.listarMateriasNoAsignadas(idDocente);
                this.listarMateriasAsignadas(idDocente);
                NotificationManager.success(response.data);
            }).catch((error) => {
                //error.response.data es lo que arrojo el servidor en caso de error
                console.log(error.response.data);
                NotificationManager.error(error.response.data.error);
            });
    }

    listarMateriasNoAsignadas(idDocente) {
        axios.get(`${urlBase}/api/materias/listarNoAsociadas/${idDocente}`)
            .then(response => {
                this.setState({
                    modalMaterias: true,
                    listaMateriasNoAsignadas: response.data
                });
            }).catch((error) => {
                console.log(error.response.data);
            });
    }

    listarMateriasAsignadas(idDocente) {
        axios.get(`${urlBase}/api/docentes/docenteMateria/${idDocente}`)
            .then(response => {
                let materias = [];
                [...response.data].map((docente) => {
                    materias.push(docente.materia);
                });
                this.setState({
                    modalMaterias: true,
                    listaMateriasAsignadas: materias
                });
            }).catch((error) => {
                console.log(error.response.data);
            });
    }

    eliminarDocenteMateria(idMateria) {
        let idDocente = this.state.idDocente;
        console.log(idDocente);
        console.log(idMateria);
        axios.delete(`${urlBase}/api/docentes/eliminarDocenteMateria/${idDocente}/${idMateria}`)
            .then(response => {
                this.listarMateriasNoAsignadas(idDocente);
                this.listarMateriasAsignadas(idDocente);
                this.listarDocentes();
                NotificationManager.success("Materia eliminada correctamente");
            }).catch((error) => {
                //error.response.data es lo que arrojo el servidor en caso de error
                console.log(error.response.data);
                NotificationManager.error(error.response.data.error);
            });
    }

    //Metodos
    solicitarInsercion() {
        this.setState({
            formulario: { direccion: {} },
            tipoModal: 'insertar'
        });
        this.cambiarEstadoModalFormulario();
    }

    cambiarEstadoModalFormulario() {
        this.setState({ modalFormulario: !this.state.modalFormulario });
    }

    cambiarEstadoModalEliminar() {
        this.setState({ modalEliminar: !this.state.modalEliminar });
    }

    cambiarEstadoModalMaterias() {
        this.setState({ modalMaterias: !this.state.modalMaterias });
    }

    capturarData = async e => {
        if (e.target.name === "direccion" || e.target.name === "barrio") {
            await this.setState({
                formulario: {
                    ...this.state.formulario,
                    direccion: {
                        ...this.state.formulario.direccion,
                        [e.target.name]: e.target.value
                    }
                }
            });
        } else {
            await this.setState({
                formulario: {
                    ...this.state.formulario,
                    [e.target.name]: e.target.value
                }
            });
        }
    }

    capturarDocente(docente) {
        this.setState({
            tipoModal: 'editar',
            formulario: {
                id: docente.id,
                cedula: docente.cedula,
                nombre: docente.nombre,
                apellido: docente.apellido,
                correo: docente.correo,
                fechaNacimiento: docente.fechaNacimiento,
                direccion: {
                    barrio: docente.direccion.barrio,
                    direccion: docente.direccion.direccion
                }
            }
        });
        this.cambiarEstadoModalFormulario();
    }

    capturarDocenteEliminar(docente) {
        this.setState({
            modalEliminar: true,
            formulario: {
                id: docente.id,
                cedula: docente.cedula,
                nombre: docente.nombre,
                apellido: docente.apellido,
                correo: docente.correo,
                fechaNacimiento: docente.fechaNacimiento,
                direccion: {
                    barrio: docente.direccion.barrio,
                    direccion: docente.direccion.direccion
                }
            }
        });
    }

    capturarFecha(date) {
        this.setState({ formulario: { ...this.state.formulario, fechaNacimiento: date } });
    }

    listarMaterias(idDocente) {
        this.setState({idDocente: idDocente});
        this.listarMateriasNoAsignadas(idDocente);
        this.listarMateriasAsignadas(idDocente);
    }

    render() {
        const { formulario } = this.state; //formulario es this.state.formulario
        return (
            <div className="container">
                <div className="row">
                    <div className="col-4">
                        <Button
                            style={{ background: "rgb(58, 183, 17)", fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }}
                            className="btn btn-dark"
                            variant="contained"
                            startIcon={<AddCircleOutlineIcon />}
                            type="submit"
                            onClick={this.solicitarInsercion}
                        >
                            Agregar Docente
                        </Button>{''}
                    </div>

                    <div className="col-8"></div>

                    <div className="col-12 m-3"></div>

                    <div className="col-12">
                        <TableContainer style={{ background: "rgb(245,245,245)", position: 'relative', overflow: 'auto', maxHeight: 400 }}>
                            <Table aria-label="simple table" size="small">
                                <TableHead>
                                    <TableRow style={{ background: "rgb(200,200,200)" }}>
                                        <TableCell style={{ fontWeight: 'bold' }}>Cedula</TableCell>
                                        <TableCell style={{ fontWeight: 'bold' }}>Nombre</TableCell>
                                        <TableCell style={{ fontWeight: 'bold' }}>Apellido</TableCell>
                                        <TableCell style={{ fontWeight: 'bold' }}>Correo</TableCell>
                                        <TableCell style={{ fontWeight: 'bold' }}>Fecha Nacimiento</TableCell>
                                        <TableCell style={{ fontWeight: 'bold' }}>Barrio</TableCell>
                                        <TableCell style={{ fontWeight: 'bold' }}>Direccion</TableCell>
                                        <TableCell style={{ fontWeight: 'bold' }}>Materias</TableCell>
                                        <TableCell style={{ fontWeight: 'bold' }}>Acciones</TableCell>
                                    </TableRow>
                                </TableHead>
                                <TableBody>
                                    {[...this.state.listaDocentes].map((docente) => {
                                        return (
                                            <TableRow key={docente.id}>
                                                <TableCell>{docente.cedula}</TableCell>
                                                <TableCell>{docente.nombre}</TableCell>
                                                <TableCell>{docente.apellido}</TableCell>
                                                <TableCell>{docente.correo}</TableCell>
                                                <TableCell>{docente.fechaNacimientoFormato}</TableCell>
                                                <TableCell>{docente.direccion.barrio}</TableCell>
                                                <TableCell>{docente.direccion.direccion}</TableCell>
                                                <TableCell>
                                                    <IconButton onClick={this.listarMaterias.bind(this, docente.id)}>
                                                        <MenuBookIcon style={{color: 'black'}}></MenuBookIcon>
                                                    </IconButton>
                                                </TableCell>
                                                <TableCell>
                                                    <IconButton onClick={this.capturarDocente.bind(this, docente)}>
                                                        <EditIcon color="primary"></EditIcon>
                                                    </IconButton>

                                                    <IconButton onClick={this.capturarDocenteEliminar.bind(this, docente)}>
                                                        <DeleteIcon color="error"></DeleteIcon>
                                                    </IconButton>
                                                </TableCell>
                                            </TableRow>
                                        );
                                    })}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </div>

                    {/*Modal de Formulario (registrar y editar)*/}
                    <Modal isOpen={this.state.modalFormulario}>
                        <ModalHeader style={{ display: 'block' }}>
                            {this.state.tipoModal === 'insertar' ?
                                "Registrar Docente" :
                                "Editar Docente"
                            }

                            <IconButton style={{ float: 'right' }} onClick={this.cambiarEstadoModalFormulario}>
                                <CloseIcon color="inherit"></CloseIcon>
                            </IconButton>

                        </ModalHeader>

                        <ModalBody>
                            <div className="form-group">
                                <label htmlFor="cedula">Cédula</label>
                                <input className="form-control" type="number" name="cedula" id="cedula" required onChange={this.capturarData} value={formulario.cedula ? formulario.cedula : ''}></input>
                                <br />
                                <label htmlFor="nombre">Nombre</label>
                                <input className="form-control" type="text" name="nombre" id="nombre" onChange={this.capturarData} value={formulario.nombre ? formulario.nombre : ''}></input>
                                <br />
                                <label htmlFor="apellido">Apellido</label>
                                <input className="form-control" type="text" name="apellido" id="apellido" onChange={this.capturarData} value={formulario.apellido ? formulario.apellido : ''}></input>
                                <br />
                                <label htmlFor="correo">Correo</label>
                                <input className="form-control" type="text" name="correo" id="correo" onChange={this.capturarData} value={formulario.correo ? formulario.correo : ''}></input>
                                <br />
                                <label htmlFor="fechaNacimiento">Fecha de nacimiento</label>
                                <br />
                                <DatePicker
                                    onChange={this.capturarFecha}
                                    selected={formulario.fechaNacimiento ? new Date(this.state.formulario.fechaNacimiento) : ''}
                                    maxDate={new Date().setMonth(new Date().getMonth() - 240)}
                                    dateFormat="dd/MM/yyyy"
                                ></DatePicker>
                                <br />
                                <br />
                                <label htmlFor="barrio">Barrio</label>
                                <input className="form-control" type="text" name="barrio" id="barrio" onChange={this.capturarData} value={formulario.direccion.barrio ? formulario.direccion.barrio : ''}></input>
                                <br />
                                <label htmlFor="direccion">Dirección</label>
                                <input className="form-control" type="text" name="direccion" id="direccion" onChange={this.capturarData} value={formulario.direccion.direccion ? formulario.direccion.direccion : ''}></input>
                                <br />
                            </div>
                        </ModalBody>

                        <ModalFooter>
                            {this.state.tipoModal === 'insertar' ?
                                <Button style={{ background: "rgb(58, 183, 17)", fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark mr-2" variant="contained" startIcon={<AssignmentTurnedInIcon />} type="submit" onClick={this.registrarDocente}>Insertar</Button> :
                                <Button style={{ background: "rgb(58, 183, 17)", fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark mr-2" variant="contained" startIcon={<EditIcon />} type="submit" onClick={this.editarDocente}>Editar</Button>
                            }
                            <Button style={{ background: "gray", fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark ml-2" variant="contained" startIcon={<CancelIcon />} type="submit" onClick={this.cambiarEstadoModalFormulario}>Cancelar</Button>{''}
                        </ModalFooter>
                    </Modal>

                    {/*Modal de confirmacion al eliminar*/}
                    <Modal isOpen={this.state.modalEliminar}>
                        <ModalHeader style={{ display: 'block' }}>
                            Confirmación
                            <IconButton style={{ float: 'right' }} onClick={this.cambiarEstadoModalEliminar}>
                                <CloseIcon color="inherit"></CloseIcon>
                            </IconButton>
                        </ModalHeader>

                        <ModalBody>
                            ¿Está seguro de eliminar este docente?
                        </ModalBody>

                        <ModalFooter>
                            <Button style={{ background: "red", fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark mr-2" variant="contained" startIcon={<RestoreFromTrashIcon />} type="submit" onClick={this.eliminarDocente}>Eliminar</Button>
                            <Button style={{ background: "gray", fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark ml-2" variant="contained" startIcon={<CancelIcon />} type="submit" onClick={this.cambiarEstadoModalEliminar}>Cancelar</Button>{''}
                        </ModalFooter>
                    </Modal>

                    {/*Modal asociar materias*/}
                    <Modal isOpen={this.state.modalMaterias}>
                        <ModalHeader style={{ display: 'block' }}>
                            Materias
                            <IconButton style={{ float: 'right' }} onClick={this.cambiarEstadoModalMaterias}>
                                <CloseIcon color="inherit"></CloseIcon>
                            </IconButton>
                        </ModalHeader>

                        <ModalBody>
                            <p style={{ fontWeight: 'bold' }}>Materias no asignadas</p>
                            {/*Tabla con materias sin asociar*/}
                            <TableContainer style={{ background: "rgb(245,245,245)", position: 'relative', overflow: 'auto', maxHeight: 250 }}>
                                <Table aria-label="simple table" size="small">
                                    <TableHead>
                                        <TableRow style={{ background: "rgb(200,200,200)" }}>
                                            <TableCell style={{ fontWeight: 'bold' }}>Materia</TableCell>
                                            <TableCell style={{ fontWeight: 'bold' }}>Registrar</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {[...this.state.listaMateriasNoAsignadas].map((materia) => {
                                            return (
                                                <TableRow key={materia.id}>
                                                    <TableCell>{materia.nombre}</TableCell>
                                                    <TableCell>
                                                        <IconButton onClick={this.asociarDocenteMateria.bind(this, materia.id)}>
                                                            <AddCircleOutlineIcon style={{color: 'rgb(58, 183, 17)'}}></AddCircleOutlineIcon>
                                                        </IconButton>
                                                    </TableCell>
                                                </TableRow>
                                            );
                                        })}
                                    </TableBody>
                                </Table>
                            </TableContainer>

                            <br/>
                            <p style={{ fontWeight: 'bold' }}>Materias asignadas</p>
                            {/*Tabla con materias asociadas*/}
                            <TableContainer style={{ background: "rgb(245,245,245)", position: 'relative', overflow: 'auto', maxHeight: 250 }}>
                                <Table aria-label="simple table" size="small">
                                    <TableHead>
                                        <TableRow style={{ background: "rgb(200,200,200)" }}>
                                            <TableCell style={{ fontWeight: 'bold' }}>Materia</TableCell>
                                            <TableCell style={{ fontWeight: 'bold' }}>Eliminar</TableCell>
                                        </TableRow>
                                    </TableHead>
                                    <TableBody>
                                        {[...this.state.listaMateriasAsignadas].map((materia) => {
                                            return (
                                                <TableRow key={materia.id}>
                                                    <TableCell>{materia.nombre}</TableCell>
                                                    <TableCell>
                                                        <IconButton onClick={this.eliminarDocenteMateria.bind(this, materia.id)}>
                                                            <DeleteIcon color="error"></DeleteIcon>
                                                        </IconButton>
                                                    </TableCell>
                                                </TableRow>
                                            );
                                        })}
                                    </TableBody>
                                </Table>
                            </TableContainer>
                        </ModalBody>
                    </Modal>
                </div>
                <NotificationContainer />
            </div>
        );
    }
}

export default Docente;