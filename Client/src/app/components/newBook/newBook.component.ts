import {Component, OnInit} from '@angular/core';
import {LibraryService} from '../../../services/library.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-new-book',
  templateUrl: './newBook.component.html',
  styleUrls: ['./newBook.component.css']
})


export class NewBookComponent implements OnInit {

  book: {
    author: string,
    id: number,
    title: string,
    description: string
  };

  constructor(private libraryService: LibraryService, private router: Router) {
    

    this.libraryService.getNewBookForm().subscribe((res: any) => {
      this.book = res;
    });

  }

  ngOnInit() {
  }

  sendForm(form) {

    this.libraryService.addNewBook(form.form.value).subscribe((res) => {


      if (res) {
        this.router.navigate(['/books']);
      } else {

      }
    });
  }
}
