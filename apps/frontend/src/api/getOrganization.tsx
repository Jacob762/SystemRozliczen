import axios from 'axios';

export async function getOrganization() {
  const { data } = await axios.get('http://localhost:8080/organization/1');
  return data;
}

export async function postOrg(){
  
}