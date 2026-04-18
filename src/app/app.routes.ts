import { Routes } from '@angular/router';
import { RoomTypeComponent } from './room-type/room-type';

export const routes: Routes = [
  { path: '', component: RoomTypeComponent },
  { path: '**', redirectTo: '' }
];