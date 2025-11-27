import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, Router } from '@angular/router';
import { RoadService, Road } from '../../services/road.service';

@Component({
  selector: 'app-roads',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './roads.component.html',
  styleUrls: ['./roads.component.css']
})
export class RoadsComponent implements OnInit {
  roads: Road[] = [];

  constructor(private roadService: RoadService, private router: Router) {}

  ngOnInit(): void {
    this.loadRoads();
  }

  loadRoads() {
    this.roadService.getAllRoads().subscribe({
      next: data => {
        // Map detectorStatus and congestionLevel for display
        this.roads = data.map(r => ({
          ...r,
          status: r.detectorStatus ? 'UP' : 'DOWN',
          congestionLevelStr: this.mapCongestionLevel(r.congestionLevel)
        }));
        console.log('Loaded roads:', this.roads);
      },
      error: err => console.error('Error loading roads:', err)
    });
  }

  mapCongestionLevel(level?: string): string {
    switch(level) {
      case 'NORMAL': return 'Low';
      case 'HIGH': return 'Medium';
      case 'VERY_HIGH': return 'High';
      default: return 'Unknown';
    }
  }

  editRoad(id?: number) {
    if (id === undefined) return;
    this.router.navigate(['/roads', id, 'edit']);
  }

  deleteRoad(id?: number) {
    if (id === undefined) return;
    if (!confirm('Delete this road?')) return;
    this.roadService.deleteRoad(id).subscribe(() => this.loadRoads());
  }
}
