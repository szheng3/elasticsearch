import { NgModule } from '@angular/core';

import { WelcomeRoutingModule } from './welcome-routing.module';

import { WelcomeComponent } from './welcome.component';
import {NgZorroAntdModule, NzPageHeaderModule} from 'ng-zorro-antd';
import {FormsModule} from '@angular/forms';
import {CommonModule} from '@angular/common';


@NgModule({
  imports: [WelcomeRoutingModule, NzPageHeaderModule, NgZorroAntdModule, FormsModule, CommonModule],
  declarations: [WelcomeComponent],
  exports: [WelcomeComponent]
})
export class WelcomeModule { }
