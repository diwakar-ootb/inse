import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// Local Modules
import { AppRoutingModule } from './app-routing.module';

// Local Components
import { AppComponent } from './app.component';
import { NetworkComponent } from './module/network/network.component';
import { MessageComponent } from './module/message/message.component';
import { NotificationComponent } from './module/notification/notification.component';
import { AccountComponent } from './module/account/account.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
