import axios from 'axios';

export async function getDocuments() {
  const { data } = await axios.get('http://localhost:8080/document/0');
  return data;
}

export async function sortDocuments(){
  await axios.post('http://localhost:8080/document/sort/0')
  return true;
}
