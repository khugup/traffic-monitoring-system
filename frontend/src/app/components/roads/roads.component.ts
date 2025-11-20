import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { RoadService } from '../../services/road.service';

@Component({
  selector: 'app-roads',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './roads.component.html',
  styleUrls: ['./roads.component.css']
  // providers: [RoadService]
})
export class RoadsComponent implements OnInit {
  roads: any[] = [];

  constructor(private roadService: RoadService) {}

  ngOnInit(): void { this.loadRoads(); }

  loadRoads() {
    this.roadService.getAllRoads().subscribe(data => this.roads = data);
  }

  deleteRoad(id: number) {
    if (!confirm('Delete this road?')) return;
    this.roadService.deleteRoad(id).subscribe(() => this.loadRoads());
  }
}
