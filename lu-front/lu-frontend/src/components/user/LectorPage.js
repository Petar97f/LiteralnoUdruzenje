import React, { Component, Fragment } from 'react';
import User from '.././user/User';
import Forms from '.././forms/Forms.js';
import ReadManuscript from "../app/ReadManuscript";
import SendToBetaReaders from "../app/SendToBetaReaders";
import Lector from "../app/Lector";

class LectorPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            formFields: [],
            form: {},
            data: [],
            showLector: false
        }
    }
    async componentDidMount () {

    }

    openLector = (e) => {
        e.preventDefault();
        this.setState({
            showLector: true
        })
    }


    render () {
        return (
            <div className="margin-top-page">
                <div className="d-flex flex-row ml-3 pt-4">
                    <div className="mt-2">
                        Editor Page:
                        /** import component here */
                        <button className="btn btn-primary" onClick={this.openLector}>Give notes</button>
                        {this.state.showLector && <Lector show={this.state.showLector} onClose={e => this.setState({showLector: false})}/>}
                    </div>
                </div>
            </div>
        );
    }
}

export default LectorPage;