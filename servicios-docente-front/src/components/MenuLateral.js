import React, { Component } from 'react';
import { Link } from 'react-router-dom';

//Componentes
import {Drawer, List, ListItem, ListItemIcon, ListItemText} from "@material-ui/core";

//Iconos
import HomeIcon from "@material-ui/icons/Home";
import PeopleAltIcon from '@material-ui/icons/PeopleAlt';
import SchoolIcon from '@material-ui/icons/School';
import MenuBookIcon from '@material-ui/icons/MenuBook';


class MenuLateral extends Component {

  constructor(props) {
    super(props);

    this.state = {
      classes: {
        drawerPaper: { width: 'inherit' },
        link: {
          textDecoration: 'none'
        }
      }
    }
  }

  render() {
    return (
      <Drawer
        style={{ width: '220px' }}
        variant="persistent"
        anchor="left"
        open={true}
        classes={{ paper: this.state.classes.drawerPaper }}
      >
        <List>
          <Link to="/" className={this.state.classes.link} style={{ textDecoration: 'none', color: '#000' }}>
            <ListItem button>
              <ListItemIcon>
                <HomeIcon />
              </ListItemIcon>
              <ListItemText primary={"Inicio"} />
            </ListItem>
          </Link>

          <Link to="/Docentes" className={this.state.classes.link} style={{ textDecoration: 'none', color: '#000' }}>
            <ListItem button>
              <ListItemIcon>
                <PeopleAltIcon />
              </ListItemIcon>
              <ListItemText primary={"Docentes"} />
            </ListItem>
          </Link>

          <Link to="/Estudiantes" className={this.state.classes.link} style={{ textDecoration: 'none', color: '#000' }}>
            <ListItem button>
              <ListItemIcon>
                <SchoolIcon />
              </ListItemIcon>
              <ListItemText primary={"Estudiantes"} />
            </ListItem>
          </Link>

          <Link to="/Materias" className={this.state.classes.link} style={{ textDecoration: 'none', color: '#000' }}>
            <ListItem button>
              <ListItemIcon>
                <MenuBookIcon />
              </ListItemIcon>
              <ListItemText primary={"Materias"} />
            </ListItem>
          </Link>
        </List>
      </Drawer>
    );
  }
}

export default MenuLateral;