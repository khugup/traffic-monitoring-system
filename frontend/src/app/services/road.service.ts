import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Road {
  id?: number;
  roadName: string;
  detectorName: string;
  detectorStatus: boolean;
  status?: string; // add this line
}



@Injectable({
  providedIn: 'root'
})
export class RoadService {

  private baseUrl = 'http://localhost:8080/road';

  constructor(private http: HttpClient) {}

  // Get all roads
  getAllRoads(): Observable<Road[]> {
    return this.http.get<Road[]>(`${this.baseUrl}/`);
  }

  // Get road by ID
  getRoadById(id: number): Observable<Road> {
    return this.http.get<Road>(`${this.baseUrl}/${id}`);
  }

  // Add new road
  addRoad(road: Road): Observable<Road> {
    return this.http.post<Road>(`${this.baseUrl}/`, road);
  }

  // Update road
  updateRoad(id: number, road: Road): Observable<Road> {
    return this.http.put<Road>(`${this.baseUrl}/${id}`, road);
  }

  // Delete road
  deleteRoad(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
