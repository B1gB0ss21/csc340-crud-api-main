<!doctype html>
<html>
<head>
  <meta charset="utf-8"><title>Animals</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-dark text-white">
<div class="container py-4">
  <div class="d-flex justify-content-between align-items-center mb-3">
    <h1 class="m-0">All Animals</h1>
    <a class="btn btn-primary" href="/animals/new">Create New</a>
  </div>

  <#if animalList?has_content>
    <div class="row g-3">
      <#list animalList as animal>
        <div class="col-sm-6 col-lg-4">
          <div class="card h-100">
            <img class="card-img-top" src="${animal.imageUrl!'/Images/default.jpg'}" alt="${animal.name!}" style="height:180px;object-fit:cover;">
            <div class="card-body">
              <h5 class="card-title">${animal.name!}</h5>
              <p class="card-text text-muted">${animal.type!'—'}</p>
              <div class="d-flex gap-2">
                <a class="btn btn-sm btn-primary" href="/animals/${animal.id}">View</a>
                <a class="btn btn-sm btn-outline-info" href="/animals/${animal.id}/edit">Edit</a>
                <a class="btn btn-sm btn-outline-danger" href="/animals/${animal.id}/delete"
                   onclick="return confirm('Delete this animal?')">Delete</a>
              </div>
            </div>
          </div>
        </div>
      </#list>
    </div>
  <#else>
    <p class="text-secondary">No animals yet.</p>
  </#if>
</div>
</body>
</html>
