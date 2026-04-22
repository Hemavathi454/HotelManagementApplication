import { Routes } from '@angular/router';
import { Login } from './core/auth/login/login';
import { Register } from './core/auth/register/register';
import { AdminDashboard } from './features/admin/AdminDashboard/AdminDashboard';
import { authGuard } from './core/guards/auth-guard';
import { roleGuard } from './core/guards/role-guard';
import { Users } from './features/admin/users/users';
import { Hotels } from './features/admin/hotels/hotels';
import { Rooms } from './features/admin/rooms/rooms';
import { Reservations } from './features/admin/reservations/reservations';
import { Payments } from './features/admin/payments/payments';
import { Reviews } from './features/admin/reviews/reviews';
import { Amenities } from './features/admin/amenities/amenities';
import { UserDashboard } from './features/user/UserDashboard/UserDashboard';
import { HotelList } from './features/user/hotel-list/hotel-list';
import { RoomList } from './features/user/room-list/room-list';
import { Booking } from './features/user/booking/booking';
import { MyReservations } from './features/user/my-reservations/my-reservations';
import { PaymentsHistory } from './features/user/payments-history/payments-history';
import { MyReviews } from './features/user/my-reviews/my-reviews';
import { RoomTypes } from './features/admin/room-types/room-types';
import { Auth } from './pages/auth/auth';
import { Home } from './pages/home/home';
import { About } from './pages/about/about';
import { Contact } from './pages/contact/contact';
import { AdminLayout } from './layout/admin-layout/admin-layout';
import { UserLayout } from './layout/user-layout/user-layout';

export const routes: Routes = [

  { path: '', component: Home },
  { path: 'auth', component: Auth },
  { path: 'admin/login', component: Auth, data: { adminOnly: true } },
  { path: 'about', component: About },
  { path: 'contact', component: Contact },

  {
    path: 'admin',
    component: AdminLayout,
    canActivate: [authGuard, roleGuard],
    data: { roles: ['ADMIN'] },
    children: [
      { path: 'dashboard', component: AdminDashboard },
      { path: 'users', component: Users },
      { path: 'hotels', component: Hotels },
      { path: 'rooms', component: Rooms },
      { path: 'room-types', component: RoomTypes },
      { path: 'amenities', component: Amenities},
      { path: 'reservations', component: Reservations},
      { path: 'payments', component: Payments},
      { path: 'reviews', component: Reviews}
    ]
  },

  {
    path: 'user',
    component: UserLayout,
    canActivate: [authGuard, roleGuard],
    data: { roles: ['CUSTOMER'] },
    children: [
      { path: 'dashboard', component: UserDashboard },
      { path: 'hotels', component: HotelList },
      { path: 'rooms', component: RoomList},
      { path: 'booking', component: Booking },
      { path: 'my-reservations', component: MyReservations },
      { path: 'payments-history', component: PaymentsHistory},
      { path: 'my-reviews', component: MyReviews}
    ]
  },

  { path: '**', redirectTo: '' }
];

