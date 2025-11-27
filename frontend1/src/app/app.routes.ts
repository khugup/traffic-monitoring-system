import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AddRoadComponent } from './components/add-road/add-road.component';
import { RoadsComponent } from './components/roads/roads.component';
import { TrafficComponent } from './components/traffic/traffic.component';
import { RoadDetailComponent } from './components/road-detail/road-detail.component';
import { RoadEditComponent } from './components/road-edit/road-edit.component'; // correct import

export const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'add-road', component: AddRoadComponent },
  { path: 'roads', component: RoadsComponent },
  { path: 'roads/:id', component: RoadDetailComponent },
  { path: 'traffic', component: TrafficComponent },
  { path: 'roads/:id/edit', component: RoadEditComponent } // edit page
];
