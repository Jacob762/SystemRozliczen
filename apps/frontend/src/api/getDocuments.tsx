import axios from 'axios';

export async function getDocuments() {
  const { data } = await axios.get('http://localhost:8080/document/1');
  return data;
}

export async function sortDocuments() {
  await axios.post('http://localhost:8080/document/sort/1');
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
  return axios.post(`http://localhost:8080/document/edit`, {
    idO: organizationId,
    idDok: documentId,
    nazwa: nazwa,
    IdK: accountantId,
    kwota: kwota,
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

export async function addDocument({
 organizationID,
 accountantID,
 nazwa,
 kwota
}: {
  organizationID: number;
  nazwa: string;
  accountantID: number;
  kwota: number;
}) {
  return axios.post(`http://localhost:8080/document/edit`, {
    organizationID: organizationID,
    name: nazwa,
    accountantID: accountantID,
    value: kwota,
  });
}
