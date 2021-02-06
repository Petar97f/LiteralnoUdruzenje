import React, { Component, Fragment } from 'react';
import User from '.././user/User';
import Forms from '.././forms/Forms.js';
import ReadManuscript from "../app/ReadManuscript";
import SendToBetaReaders from "../app/SendToBetaReaders";

class EditorPage extends Component {
  constructor(props) {
    super(props);
    this.state = {
      formFields: [],
      form: {},
      data: [],
      showManuscript: false,
      showBetaReaders: false
    }
  }
  async componentDidMount () {

  }

  openManuscript = (e) => {
    e.preventDefault();
    this.setState({
      showManuscript: true
    })
  }

  openBetaReaders = (e) => {
    e.preventDefault();
    this.setState({
      showBetaReaders: true
    })
  }

  render () {
    return (
      <div className="margin-top-page">
        <div className="d-flex flex-row ml-3 pt-4">
          <div className="mt-2">
            Editor Page:
            /** import component here */
            <button className="btn btn-primary" onClick={this.openManuscript}>Read book</button>
            <button className="btn btn-primary" onClick={this.openBetaReaders}>Send to beta readers?</button>
            {this.state.showManuscript && <ReadManuscript show={this.state.showManuscript} onClose={e => this.setState({showManuscript: false})}/>}
            {this.state.showBetaReaders && <SendToBetaReaders show={this.state.showBetaReaders} onClose={e => this.setState({showBetaReaders: false})}/>}
          </div>
        </div>
      </div>
    );
  }
}

export default EditorPage;