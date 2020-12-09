import React, { Component} from 'react';
import { withRouter } from 'react-router-dom';
import { observer } from 'mobx-react';
import AppRoutes from './AppRoutes';
import Header from './components/app/Header';
import Footer from './components/app/Footer';
import './App.scss';

class App extends Component {
  constructor (props) {
    super(props);
    this.state = {
      ready: false
    };
  }
  
  render () {
    return (
      <div className="App">
        <Header />
        <AppRoutes />
        <Footer />
      </div>
    );
  }
  
}

export default observer(withRouter(App));
