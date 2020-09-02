import NeedCandidateStatus from './NeedCandidateStatus';
import Needs from '../needs/Needs';
import Candidates from '../candidates/Candidates';

export default class CandidatesNeeds {
  id: number;
  need: Needs;
  candidate: Candidates;
  needCandidateStatus: NeedCandidateStatus; 
  dateCreation: Date;
  dateSent: Date;
  interviewDate: Date;
  dateFeedback: Date;
  dateOk: Date;
  dateKoClient: Date;
  dateKoCandidate: Date;
}





