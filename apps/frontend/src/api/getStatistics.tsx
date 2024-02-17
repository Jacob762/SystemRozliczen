import axios from 'axios';

export async function getStatistics() {
  const { data } = await axios.get('http://localhost:8080/statystyka/1');
  return data;
}
