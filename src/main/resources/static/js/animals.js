
const baseUrl = '/animals';

export async function fetchAll() {
  const res = await fetch(baseUrl);
  if (!res.ok) throw new Error('Failed to fetch animals');
  return res.json();
}

export async function fetchById(id) {
  const res = await fetch(`${baseUrl}/${id}`);
  if (!res.ok) throw new Error(`Animal ${id} not found`);
  return res.json();
}

export async function createAnimal(animal) {
  const res = await fetch(baseUrl, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(animal),
  });
  if (!res.ok) throw new Error('Create failed');
  return res.json();
}

export async function updateAnimal(id, animal) {
  const res = await fetch(`${baseUrl}/${id}`, {
    method: 'PUT',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(animal),
  });
  if (!res.ok) throw new Error('Update failed');
  return res.json();
}

export async function deleteAnimal(id) {
  const res = await fetch(`${baseUrl}/${id}`, { method: 'DELETE' });
  if (!res.ok) throw new Error('Delete failed');
}
