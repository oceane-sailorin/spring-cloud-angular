import { NbMenuItem } from '@nebular/theme';

export const MENU_ITEMS: NbMenuItem[] = [
{
    title: 'Needs Candidates',
    icon: 'options-2-outline',
    children: [
      {
        title: 'Dashboard',
        link: '/pages/candidates-needs/dashboard',
      }
     ]
 },	
{
    title: 'Candidates',
    icon: 'keypad-outline',
    children: [
      {
        title: 'Candidates dashboard',
        link: '/pages/candidates/dashboard-candidate',
      },
     {
        title: 'Candidates list',
        link: '/pages/candidates/get-candidate',
      },
      {
        title: 'Add candidate',
        link: '/pages/candidates/add-candidate',
      }
     ]
 },
{
	
    title: 'Needs',
    icon: 'layout-outline',
    children: [
      {
        title: 'Needs list',
        link: '/pages/needs/get-need',
      },
      {
        title: 'Add need',
        link: '/pages/needs/add-need',
      }
     ]
 },  
{
	
    title: 'Clients',
    icon: 'globe-outline',
    children: [
      {
        title: 'Clients list',
        link: '/pages/clients/get-client',
      },
      {
        title: 'Add client',
        link: '/pages/clients/add-client',
      }
     ]
  }, 
{
	
    title: 'Users',
    icon: 'people-outline',
    children: [
      {
        title: 'Users list',
        link: '/pages/users/get-user',
      },
      {
        title: 'Add user',
        link: '/pages/users/add-user',
      }
     ]
  },
 {
    title: 'Profiles',
    icon: 'file-text-outline',
    children: [
      {
        title: 'Profiles list',
        link: '/pages/profiles/get-profile',
      },
      {
        title: 'Add profile',
        link: '/pages/profiles/add-profile',
      }
     ]
  }

];
