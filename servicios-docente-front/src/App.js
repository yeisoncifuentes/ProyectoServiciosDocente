import React, { Component } from 'react';

import {BrowserRouter as Router, Switch, Route} from "react-router-dom";

//Componentes
import {Container, Typography} from "@material-ui/core";
import MenuLateral from './components/MenuLateral.js';
import Docente from './components/Docente.js';
import Estudiante from './components/Estudiante.js';
import Materia from './components/Materia.js';


class App extends Component {
  render() {
    return (
      <Router>
        <div style={{ display: 'flex' }}>
          <MenuLateral></MenuLateral>
          <Switch>
            <Route exact path="/">
              <Container>
                <Typography variant="h3" gutterBottom>
                  Servicios Docentes
                </Typography>
              </Container>
            </Route>

            <Route exact path="/Docentes">
              <Container>
                <Typography variant="h3" gutterBottom>
                  Docentes
                </Typography>
                <Typography variant="body1" gutterBottom>
                  <Docente></Docente>
                </Typography>
              </Container>
            </Route>

            <Route exact path="/Estudiantes">
              <Container>
                <Typography variant="h3" gutterBottom>
                  Estudiantes
                </Typography>
                <Typography variant="body1" gutterBottom>
                  <Estudiante></Estudiante>
                </Typography>
              </Container>
            </Route>

            <Route exact path="/Materias">
              <Container>
                <Typography variant="h3" gutterBottom>
                  Materias
                </Typography>
                <Typography variant="body1" gutterBottom>
                  <Materia></Materia>
                </Typography>
              </Container>
            </Route>
          </Switch>
        </div>
      </Router >
    );
  }
}

export default App;