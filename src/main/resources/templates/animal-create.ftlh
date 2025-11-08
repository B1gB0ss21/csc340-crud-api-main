<!doctype html>
<html>
<head>
  <meta charset="utf-8"><title>Create Animal</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-dark text-white">
<div class="container py-4" style="max-width:900px;">
  <a class="btn btn-outline-light btn-sm mb-3" href="/animals">Back</a>
  <h1>Create New Animal</h1>

  <form method="post" action="/animals/new" class="vstack gap-3">
    <div class="row g-3">
      <div class="col-md-6">
        <label class="form-label">Name</label>
        <input class="form-control" name="name" required>
      </div>
      <div class="col-md-6">
        <label class="form-label">Type / Breed</label>
        <input class="form-control" name="type">
      </div>
      <div class="col-md-4">
        <label class="form-label">Age</label>
        <input class="form-control" type="number" name="age" min="0">
      </div>
      <div class="col-md-8">
        <label class="form-label">Image URL</label>
        <input class="form-control" name="imageUrl">
      </div>
      <div class="col-12">
        <label class="form-label">Description</label>
        <textarea class="form-control" name="description" rows="4"></textarea>
      </div>
    </div>
    <div class="d-flex gap-2">
      <button class="btn btn-primary" type="submit">Save</button>
      <a class="btn btn-secondary" href="/animals">Cancel</a>
    </div>
  </form>
</div>
</body>
</html>
