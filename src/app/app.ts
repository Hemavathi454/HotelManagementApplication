import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { About } from "./pages/about/about";
import { AdminLayout } from "./layout/admin-layout/admin-layout";
import { AdminDashboard } from "./features/admin/AdminDashboard/AdminDashboard";
import { Amenities } from "./features/admin/amenities/amenities";
import { Auth } from "./pages/auth/auth";
import { Booking } from "./features/user/booking/booking";
import { ConfirmDialog } from "./shared/components/confirm-dialog/confirm-dialog";
import { Contact } from "./pages/contact/contact";
import { Home } from "./pages/home/home";
import { HotelList } from "./features/user/hotel-list/hotel-list";
import { Hotels } from "./features/admin/hotels/hotels";
import { LoadingSpinner } from "./shared/components/loading-spinner/loading-spinner";
import { Login } from "./core/auth/login/login";
import { MyReservations } from "./features/user/my-reservations/my-reservations";
import { MyReviews } from "./features/user/my-reviews/my-reviews";
import { Navbar } from "./layout/navbar/navbar";
import { Payments } from "./features/admin/payments/payments";
import { PaymentsHistory } from "./features/user/payments-history/payments-history";
import { Register } from "./core/auth/register/register";
import { Reservations } from "./features/admin/reservations/reservations";
import { Reviews } from "./features/admin/reviews/reviews";
import { RoomList } from "./features/user/room-list/room-list";
import { RoomTypes } from "./features/admin/room-types/room-types";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, About, AdminLayout, AdminDashboard, Amenities, Auth, Booking, ConfirmDialog, Contact, Home, HotelList, Hotels, LoadingSpinner, Login, MyReservations, MyReviews, Navbar, Payments, PaymentsHistory, Register, Reservations, Reviews, RoomList, RoomTypes],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected title = 'hotel-management';
}
