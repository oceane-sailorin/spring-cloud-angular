import CandidateStatus from './CandidateStatus';
import Users from '../users/Users';
import Countries from './Countries';
import Sources from './Sources';

export default class Candidates {
  id: number;
  identity: string;
  gender: string;
  lastname: string;
  firstname: string;
  email: string;
  phone: string;
  address: string;
  country: Countries;
  recruiter: Users;
  sourcer: Users;
  source: Sources;
  candidateStatus: CandidateStatus;
  stack: string;
  profile: string;
  salary: string; 
  dateDisponibility: Date;
  dateSiiInterview: Date;
  dateSourcing: Date;
  dateProposition: Date;
  dateSignature: Date;
  dateLastUpdate: Date;
  keywords: string[];
  filename: String;
}





