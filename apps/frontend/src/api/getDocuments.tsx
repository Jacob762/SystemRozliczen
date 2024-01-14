import axios from 'axios';

export async function getDocuments() {
  const { data } = await axios.get('http://localhost:8080/document/0');
  return data;
}

export async function sortDocuments() {
  await axios.post('http://localhost:8080/document/sort/0');
  return true;
}

export async function editDocument({
  organizationId,
  documentId,
  nazwa,
  accountantId,
  kwota,
}: {
  organizationId: number;
  documentId: number;
  nazwa: string;
  accountantId: number;
  kwota: number;
}) {
  return axios.post(`http://localhost:8080/document`, {
    idO: organizationId,
    idDok: documentId,
    Nazwa: nazwa,
    IdK: accountantId,
    Kwota: kwota,
  });
}

export async function deleteDocument({
  organizationId,
  documentId,
}: {
  organizationId: number;
  documentId: number;
}) {
  return axios.delete(
    `http://localhost:8080/document/${organizationId}/${documentId}`,
  );
}
