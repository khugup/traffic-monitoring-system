import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Road {
  id?: number;             // optional when creating new road
  roadName: string;
  detectorName: string;
  detectorStatus: boolean;

  // backend values
  congestionLevel?: string;

  // UI-only values
  status?: string;
  congestionLevelStr?: string;
}


@Injectable({
  providedIn: 'root'
})
export class RoadService {

  private apiUrl = 'http://localhost:8080/roads'; // must match backend

  constructor(private http: HttpClient) {}

  // GET all roads
  getAllRoads(): Observable<Road[]> {
    return this.http.get<Road[]>(`${this.apiUrl}/`);
  }

  // GET road by ID
  getRoadById(id: number): Observable<Road> {
    return this.http.get<Road>(`${this.apiUrl}/${id}`);
  }

  // CREATE road
  addRoad(road: Road): Observable<Road> {
    return this.http.post<Road>(`${this.apiUrl}/`, road);
  }

  // UPDATE road
  updateRoad(id: number, road: Road): Observable<Road> {
    return this.http.put<Road>(`${this.apiUrl}/${id}`, road);
  }

  // DELETE road
  deleteRoad(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}
