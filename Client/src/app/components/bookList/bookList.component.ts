import {Component, OnInit} from '@angular/core';
import {LibraryService} from '../../../services/library.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './bookList.component.html',
  styleUrls: ['./bookList.component.css']
})

export class BookListComponent implements OnInit {

  booksList: any;

  constructor(private libraryService: LibraryService) {
  }

  ngOnInit() {
    this.getBooks();
  }

  getBooks() {
    this.libraryService.getAllBooks().subscribe((books: any) => {
      this.booksList = books;
    });
  }
  
  deleteBook(id) {
    this.libraryService.deleteBook(id).subscribe( (res) => {

      if (res) {
        this.getBooks();
      }
    })
  }

  changeReadStatus(id) {
    this.libraryService.changeReadStatus(id).subscribe((res) => {
      this.getBooks();
    });
  }
}
