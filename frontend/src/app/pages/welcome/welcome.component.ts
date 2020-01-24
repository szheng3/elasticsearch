import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ResponseTs} from 'src/app/pages/welcome/ResponseTs';
import {map, tap} from 'rxjs/operators';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.scss'],
})
export class WelcomeComponent implements OnInit {

  data$: Observable<ResponseTs>;
  hotTags = ['Movie', 'Books', 'Music', 'Sports'];
  selectedTags: string[] = [];
  q: string;
  index = 1;
  selectedStatus = '';
  category = [];
  selectedCategory = [];
  rangeValue = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.data$ = this.http.get<ResponseTs>('http://localhost:8080/ecommerce?q=');

  }

  onChange($event: Event) {
    // @ts-ignore
    this.q = $event.target.value;

    this.data$ = this.getData$({q: this.q, status: this.selectedStatus, category: this.selectedCategory}).
      pipe(tap(x => this.category = x.agg.category.map((v) => v.key)),
      );
  }

  getData$(param) {
    const query = Object.keys(param).map(key => {
      if (Array.isArray(Object[key])) {
        return Object[key].map(value => key + '=' + value).join('&');
      } else {
        return key + '=' + param[key];
      }
    }).join('&');
    return this.http.get<ResponseTs>('http://localhost:8080/ecommerce?' + query).
      pipe(
        tap(value => this.hotTags = value.agg.statues.map(value1 => value1.key + '(' + value1.docCount + ')')),
      );
  }

  getParam(param) {
    return Object.keys(param).map(key => {
      if (Array.isArray(Object[key])) {
        return Object[key].map(value => key + '=' + value).join('&');
      } else {
        return key + '=' + param[key];
      }
    }).join('&');
  }

  handleChange(checked: boolean, tag: string): void {
    if (checked) {
      this.selectedCategory.push(tag);
    } else {
      this.selectedCategory = this.selectedCategory.filter(t => t !== tag);
    }
    this.data$ = this.getData$({q: this.q, status: this.selectedStatus, index: this.index - 1, category: this.selectedCategory});

  }

  onIndexChange(event: number) {
    this.index = event;

    this.data$ = this.getData$({q: this.q, status: this.selectedStatus, index: this.index - 1, category: this.selectedCategory});
  }

  selectChanged($event: any) {
    this.index = 1;
    this.data$ = this.getData$({q: this.q, status: this.selectedStatus, index: this.index - 1, category: this.selectedCategory});

  }

  onAfterChange($event: number[] | number) {
    console.log(this.rangeValue)
    console.log(`onChange: ${$event}`);

  }
}
