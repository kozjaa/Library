import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {LibraryService} from '../services/library.service';
import {BookListComponent} from './components/bookList/bookList.component';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule, Routes} from '@angular/router';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {NewBookComponent} from './components/newBook/newBook.component';
import {FormsModule} from '@angular/forms';
import { BookViewComponent } from './components/book-view/book-view.component';

const appRoutes: Routes = [
  {path: '', component: BookListComponent},
  {path: 'books', component: BookListComponent},
  {path: 'newBook', component: NewBookComponent},
  {path: 'book/:id', component: BookViewComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    BookListComponent,
    NewBookComponent,
    BookViewComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes, {enableTracing: false}),
    HttpClientModule,
    FormsModule,
    NgbModule.forRoot()
  ],
  exports: [
    RouterModule
  ],
  providers: [LibraryService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
