import axios from 'axios';

export async function getAccountant() {
  const { data } = await axios.get('http://localhost:8080/accountant/1');
  return data;
}

