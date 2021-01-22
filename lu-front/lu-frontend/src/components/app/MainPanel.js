import React, { Component} from 'react'
import { withRouter } from 'react-router-dom';
import { observer } from 'mobx-react';

class MainPanel extends Component {

  render () {
    return (
      <div className="main-container">
      </div>
    );
  }
  
}

export default observer(withRouter(MainPanel));
