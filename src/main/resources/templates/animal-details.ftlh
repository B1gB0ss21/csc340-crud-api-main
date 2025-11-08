<!doctype html>
<html>
<head>
  <meta charset="utf-8"><title>${animal.name!} • Details</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-dark text-white">
<div class="container py-4">
  <a class="btn btn-outline-light btn-sm mb-3" href="/animals">Back</a>

  <div class="row g-4">
    <div class="col-lg-6">
      <div class="card h-100">
        <img class="card-img-top" src="${animal.imageUrl!'/Images/default.jpg'}" alt="${animal.name!}" style="height:360px;object-fit:cover;">
        <div class="card-body">
          <h1 class="card-title mb-2">${animal.name!}</h1>
          <span class="badge text-bg-secondary me-2">${animal.type!'—'}</span>
          <span class="badge text-bg-secondary">${animal.age!'—'} yrs</span>
          <p class="card-text mt-3">${animal.description!'No description.'}</p>
          <div class="d-flex gap-2">
            <a class="btn btn-info" href="/animals/${animal.id}/edit">Edit</a>
            <a class="btn btn-secondary" href="/animals">All</a>
          </div>
        </div>
      </div>
    </div>

    <div class="col-lg-6">
      <div class="card h-100">
        <div class="card-body">
          <h5>Meta</h5>
          <ul class="list-group list-group-flush">
            <li class="list-group-item bg-transparent text-white d-flex justify-content-between">
              <span>ID</span><span>${animal.id}</span>
            </li>
            <li class="list-group-item bg-transparent text-white d-flex justify-content-between">
              <span>Date of Birth</span><span>${animal.dateOfBirth!'—'}</span>
            </li>
            <li class="list-group-item bg-transparent text-white d-flex justify-content-between">
              <span>Last Updated</span><span>${animal.updatedAt!'—'}</span>
            </li>
          </ul>
        </div>
      </div>
    </div>

  </div>
</div>
</body>
</html>
