import { extendObservable } from 'mobx';

class User {
  constructor() {
    extendObservable(this, {
      isLoggedIn: false,
      isAdmin: false,
      role: '',
      userId: '',
      username: '',
      email: '',
      name: '',
      city: '',
      country: ''
    });
  }
}

export default new User();