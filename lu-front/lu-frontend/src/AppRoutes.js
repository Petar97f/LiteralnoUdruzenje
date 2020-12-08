import React, { Component, Suspense } from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';
import MainPanel from './components/app/MainPanel';
import UserPage from './components/user/UserPage';
import About from './components/app/About';
import './App.scss';
import User from './components/user/User';

class AppRoutes extends Component {
  render () {
		if (User.isLoggedIn) {
			return (
				<Suspense>
					<Switch>
						<Route exact path="/user" component={UserPage} />
						<Route exact path="/about" component={About} />
						<Redirect to="/user" />
					</Switch>
				</Suspense>
			);
		} else {
			return (
				<Suspense>
					<Switch>
						<Route exact path="/" component={MainPanel} />
						<Redirect to="/" />
					</Switch>
				</Suspense>
			);
		}
		
	}
}


export default AppRoutes;