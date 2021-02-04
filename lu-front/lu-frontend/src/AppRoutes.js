import React, { Component, Suspense } from 'react';
import { Switch, Route, Redirect } from 'react-router-dom';
import MainPanel from './components/app/MainPanel';
import UserPage from './components/user/UserPage';
import About from './components/app/About';
import './App.scss';
import User from './components/user/User';
import Membership  from './components/user/Membership';
import SubmitBook  from './components/user/SubmitBook';
import Member from './components/user/Member';
import AdminMemberPanel from './components/user/AdminMemberPanel';
import ReadManuscript from "./components/app/ReadManuscript";
import jwt from 'jwt-decode' 

class AppRoutes extends Component {
	
	componentDidMount () {
		console.log(User)
	}

  render () {
		if (User.isLoggedIn) {
			if(User.role === "SYS_ADMIN") {
				return (
					<Suspense>
						<Switch>
							<Route exact path="/user" component={UserPage} />
							<Route exact path="/about" component={About} />
							<Redirect to="/user" />
						</Switch>
					</Suspense>
				);
			} else if (User.role === "ROLE_WRITER") {
				return (
					<Suspense>
						<Switch>
							<Route exact path="/user" component={UserPage} />
							<Route exact path="/about" component={About} />
							<Route exact path="/membership" component={Membership} />
							<Route exact path="/submitBook" component={SubmitBook} />
							<Redirect to="/user" />
						</Switch>
					</Suspense>
				);
			} else if (User.role === "ROLE_BETA_READER") {
				return (
					<Suspense>
						<Switch>
							<Route exact path="/user" component={UserPage} />
							<Route exact path="/about" component={About} />
							<Route exact path="/membership" component={Membership} />
							<Redirect to="/user" />
						</Switch>
					</Suspense>
				);
			} else if (User.role === "WRITTER") {
				return (
					<Suspense>
						<Switch>
							<Route exact path="/user" component={UserPage} />
							<Route exact path="/about" component={About} />
							<Route exact path="/membership" component={Membership} />
							<Route exact path="/submitBook" component={SubmitBook} />
							<Redirect to="/user" />
						</Switch>
					</Suspense>
				);
			} else if (User.role === "ROLE_LECTOR") {
				return (
					<Suspense>
						<Switch>
							<Route exact path="/user" component={UserPage} />
							<Route exact path="/about" component={About} />
							<Redirect to="/user" />
						</Switch>
					</Suspense>
				);
			} else if (User.role === "ROLE_EDITOR") {
				return (
					<Suspense>
						<Switch>
							<Route exact path="/user" component={UserPage} />
							<Route exact path="/about" component={About} />
							<Route exact path="/read" component={ReadManuscript}/>
							<Redirect to="/user" />
						</Switch>
					</Suspense>
				);
			} else if (User.role === "ROLE_BOARD_MEMBER") {
				return (
					<Suspense>
						<Switch>
							<Route exact path="/member" component={Member} />
							<Route exact path="/about" component={About} />
							<Redirect to="/member" />
						</Switch>
					</Suspense>
				);
			} else if (User.role === "ROLE_ADMIN_BOARD_MEMBER") {
				return (
					<Suspense>
						<Switch>
							<Route exact path="/admin" component={AdminMemberPanel} />
							<Route exact path="/about" component={About} />
							<Redirect to="/admin" />
						</Switch>
					</Suspense>
				);
			}
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