import { Component, OnInit } from '@angular/core';
import {LibraryService} from '../../../services/library.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-book-view',
  templateUrl: './book-view.component.html',
  styleUrls: ['./book-view.component.css']
})
export class BookViewComponent implements OnInit {

  book: {title: string, author: string, description: string};
  
  constructor(private libraryService: LibraryService, private route: ActivatedRoute, private router: Router) {
    const id = this.route.snapshot.params['id'];
    this.libraryService.getBookById(id).subscribe( (book: any) => {
      
      if (!book) {
        this.router.navigate(['/books']);
      } else {
        this.book = book;
      }
    });
  }

  ngOnInit() {
    
  }

  deleteBook(id) {
    this.libraryService.deleteBook(id).subscribe( (res) => {

      if (res) {
        this.router.navigate(['/books']);
      }
      
    })
  }

}
