import Clients from '../clients/Clients';
import Users from '../users/Users';
import Priorities from './Priorities';
import NeedsStatuses from './NeedsStatuses';

export default class Needs {
  id: number;
  title: string;
  status: NeedsStatuses;
  priority: Priorities;
  poste: string;
  departement: string;
  description: string;
  client: Clients;
  commercial: Users; 
  dateCreation: Date;
  dateNeed: Date;
  dateEnd: Date;
  keywords: string[];
  filename: String;
}
