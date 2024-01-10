import axios from 'axios';

export async function getAccountant() {
  const { data } = await axios.get('http://localhost:8080/accountant', {
    data: {
      IdOrg: 0,
      IdKsieg: 0,
    },
  });
  return data;
}
