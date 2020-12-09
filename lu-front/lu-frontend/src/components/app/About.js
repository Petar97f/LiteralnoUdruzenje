import React, { Component} from 'react';
import User from '.././user/User';

class About extends Component {
	constructor(props) {
		super(props);
		this.state = {

		}
	}

	async componentDidMount () {
		
	}

	render() {
		return (
			<div className="margin-top-page ml-4">
				<div className="pt-4">
					<label>First Name: {User.name}</label>
					<br/>
					<label>Email: {User.email}</label>
				</div>
			</div>
		)
	}
}

export default About;
