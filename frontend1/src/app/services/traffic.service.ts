import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TrafficDataDto } from '../models/traffic-data.dto';

@Injectable({
  providedIn: 'root'
})
export class TrafficService {

  private baseUrl = 'http://localhost:8080/traffic/';

  constructor(private http: HttpClient) {}

  getAllTraffic(): Observable<TrafficDataDto[]> {
    return this.http.get<TrafficDataDto[]>(this.baseUrl);
  }
}
