import { extendObservable } from 'mobx';

class User {
  constructor() {
    extendObservable(this, {
      isLoggedIn: false,
      isAdmin: false,
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