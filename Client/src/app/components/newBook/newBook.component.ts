import {Component, OnInit} from '@angular/core';
import {LibraryService} from '../../../services/library.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
    selector: 'app-new-book',
    templateUrl: './newBook.component.html',
    styleUrls: ['./newBook.component.css']
})


export class NewBookComponent implements OnInit {

    author: string;
    id: number;
    title: string;
    description: string;
    formInvalid: boolean;

    constructor(private libraryService: LibraryService, private router: Router, private route: ActivatedRoute) {

        const id = this.route.snapshot.params['id'];
        this.formInvalid = false;

        if (id) {
            this.libraryService.getBookById(id).subscribe((book: any) => {

                if (!book) {
                    this.router.navigate(['/books']);
                } else {
                    this.author = book.author;
                    this.id = book.id;
                    this.title = book.title;
                    this.description = book.description;
                }
            });
        } else {

            this.libraryService.getNewBookForm().subscribe((book: any) => {
                this.author = book.author;
                this.id = book.id;
                this.title = book.title;
                this.description = book.description;

            });
        }

    }

    ngOnInit() {
    }

    sendForm(form) {

        if (form.form.status === 'INVALID') {
            this.formInvalid = true;
        } else {
            this.formInvalid = false;

            const book = {author: this.author, id: this.id, title: this.title, description: this.description};
            this.libraryService.addNewBook(book).subscribe((res) => {

                if (res) {
                    this.router.navigate(['/books']);
                } else {

                }
            });
        }
    }
}
