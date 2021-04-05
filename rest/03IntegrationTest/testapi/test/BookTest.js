const assert = require('assert');

Feature('books');

Scenario('Get all books', async ({I}) => {
  let books = await I.sendGetRequest('/api/books');
  assert.ok(books.data.length >= 3);
});

Scenario('Get a single book', async ({I}) => {
  let user = await I.sendGetRequest('/api/books/1');
  //Lấy user có id == 1 thì dữ liệu 
  assert.strictEqual(user.data.id, 1);
});


Scenario('Create new user', async ({I}) => {
  let books = await I.sendGetRequest('/api/books');
  let number_books_before_add_new = books.data.length;

  await I.sendPostRequest('/api/books/',
    { 'author': 'Tô Hoài', 'title' : 'Dế Mèn Phiêu Lưu Ký' }
  );

  books = await I.sendGetRequest('/api/books');
  //Nếu thêm mới thành công thì số lượng user phải tăng lên một
  assert.strictEqual(number_books_before_add_new + 1, books.data.length);
});


Scenario('Delete a book', async ({I}) => {

  let new_id = await I.sendPostRequest('/api/books',
    { 'author': 'Mark Twain', 'title' : 'Tom Sawyer' }
  );
  let result = await I.sendDeleteRequest('/api/books/' + new_id.data['id']);
  assert.strictEqual(result.status, 200);
});