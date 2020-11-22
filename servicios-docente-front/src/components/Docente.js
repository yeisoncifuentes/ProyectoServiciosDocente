import React, { Component } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

//Componentes
import { Modal, ModalHeader, ModalBody, ModalFooter } from 'reactstrap';
import { NotificationManager } from 'react-notifications';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';

//Iconos
import AddCircleOutlineIcon from '@material-ui/icons/AddCircleOutline';
import EditIcon from '@material-ui/icons/Edit';
import DeleteIcon from '@material-ui/icons/Delete';
import RestoreFromTrashIcon from '@material-ui/icons/RestoreFromTrash';
import CancelIcon from '@material-ui/icons/Cancel';

const urlBase = 'http://localhost:9090/ProyectoServiciosDocente-web';


class Docente extends Component {

    constructor(props) {
        super(props);

        this.state = {
            listaDocentes: [],
            modalInsertar: false,
            formulario: {
                cedula: '',
                nombre: '',
                apellido: '',
                correo: '',
                fechaNacimiento: '',
                direccion: {
                    barrio: '',
                    direccion: ''
                }
            }
        }

        this.cambiarEstadoModalInsertar = this.cambiarEstadoModalInsertar.bind(this);
        this.registrarDocente = this.registrarDocente.bind(this);
    }

    componentDidMount() {
        this.listarDocentes();
    }

    registrarDocente() {
        NotificationManager.success("mensaje");
        axios.post(`${urlBase}/api/docentes/registrar`, this.state.formulario)
            .then(response => {
                this.cambiarEstadoModalInsertar();
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
                console.log(error);
            });
    }

    cambiarEstadoModalInsertar() {
        this.setState({ modalInsertar: !this.state.modalInsertar });
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

    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-4">
                        <Button style={{ background: "rgb(58, 183, 17)", fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark" variant="contained" startIcon={<AddCircleOutlineIcon />} type="submit" onClick={this.cambiarEstadoModalInsertar}>Agregar Docente</Button>{''}
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
                                                    <IconButton>
                                                        <EditIcon color="primary"></EditIcon>
                                                    </IconButton>

                                                    <IconButton>
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

                    <Modal isOpen={this.state.modalInsertar}>
                        <ModalHeader style={{ display: 'block' }}>
                            Registrar Docente
                        </ModalHeader>

                        <ModalBody>
                            <div className="form-group">
                                <label htmlFor="cedula">Cédula</label>
                                <input className="form-control" type="number" name="cedula" id="cedula" onChange={this.capturarData}></input>
                                <br />
                                <label htmlFor="nombre">Nombre</label>
                                <input className="form-control" type="text" name="nombre" id="nombre" onChange={this.capturarData}></input>
                                <br />
                                <label htmlFor="apellido">Apellido</label>
                                <input className="form-control" type="text" name="apellido" id="apellido" onChange={this.capturarData}></input>
                                <br />
                                <label htmlFor="correo">Correo</label>
                                <input className="form-control" type="text" name="correo" id="correo" onChange={this.capturarData}></input>
                                <br />
                                <label htmlFor="fechaNacimiento">Fecha de nacimiento</label>
                                <input className="form-control" type="text" name="fechaNacimiento" id="fechaNacimiento" onChange={this.capturarData}></input>
                                <br />
                                <label htmlFor="barrio">Barrio</label>
                                <input className="form-control" type="text" name="barrio" id="barrio" onChange={this.capturarData}></input>
                                <br />
                                <label htmlFor="direccion">Dirección</label>
                                <input className="form-control" type="text" name="direccion" id="direccion" onChange={this.capturarData}></input>
                                <br />
                            </div>
                        </ModalBody>

                        <ModalFooter>
                            <Button style={{ background: "rgb(58, 183, 17)", fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark mr-2" variant="contained" startIcon={<RestoreFromTrashIcon />} type="submit" onClick={this.registrarDocente}>Insertar</Button>{''}
                            <Button style={{ background: "gray", fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark ml-2" variant="contained" startIcon={<CancelIcon />} type="submit" onClick={this.cambiarEstadoModalInsertar}>Cancelar</Button>{''}
                        </ModalFooter>
                    </Modal>
                </div>
            </div>
        );
    }
}

export default Docente;
