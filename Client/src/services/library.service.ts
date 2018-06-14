import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable()

export class LibraryService {

  apiUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getAllBooks() {
    return this.http.get(this.apiUrl + '/books');
  }

  getNewBookForm() {
    return this.http.get(this.apiUrl + '/newbook');
  }
  
  changeReadStatus(id) {
    return this.http.get(this.apiUrl + '/book/read/' + id);
  }
  
  getBookById(id) {
    return this.http.get(this.apiUrl + '/book/' + id);
  }

  addNewBook(data) {
    console.warn(data);
    return this.http.post(this.apiUrl + '/books', data, {
      headers: {
        'Allow-Access-Control-Origin': '*',
        'Allow-Access-Control-Methods': 'POST',
        'Content-Type': 'application/json'
      }
    });
  }
}
