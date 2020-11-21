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

const urlBase = 'http://localhost:9090/ProyectoServiciosDocente-web';


class Docente extends Component {

    constructor(props) {
        super(props);

        this.state = {
            listaDocentes: []
        }
    }

    componentDidMount() {
        this.listarDocentes();
    }

    listarDocentes() {
        axios.get(`${urlBase}/api/docentes/listar/false`)
            .then(response => {
                console.log(response.data);
                this.setState({
                    listaDocentes: response.data
                });
            }).catch((error) => {
                console.log(error);
            });
    }

    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col-4">
                        <Button style={{ background: "rgb(235, 126, 21)", fontSize: "13px", fontFamily: "sans-serif", textTransform: "none" }} className="btn btn-dark" variant="contained" startIcon={<AddCircleOutlineIcon />} type="submit" onClick={this.registrarCiclosDocente}>Agregar Docente</Button>{''}
                    </div>

                    <div className="col-8"></div>

                    <div className="col-12">
                        <TableContainer style={{ background: "rgb(245,245,245)", position: 'relative', overflow: 'auto', maxHeight: 350 }}>
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
                                            </TableRow>
                                        );
                                    })}
                                </TableBody>
                            </Table>
                        </TableContainer>
                    </div>
                </div>
            </div>
        );
    }
}

export default Docente;
