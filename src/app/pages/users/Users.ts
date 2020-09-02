import Status from './Users';
import Profiles from '../profiles/Profiles';

export default class Users {
  id: number;
  profile: Profiles;
  status: Status;
  login: string;
  lastname: string;
  firstname: string;
  dateCreation: Date;
  dateActivation: Date;
  dateLastConnection: Date;
  dateArchive: Date;
}