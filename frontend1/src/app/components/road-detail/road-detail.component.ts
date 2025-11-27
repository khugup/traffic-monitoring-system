import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { RoadService, Road } from '../../services/road.service';

@Component({
  selector: 'app-road-detail',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './road-detail.component.html'
})
export class RoadDetailComponent implements OnInit {

  road: Road | null = null;

  constructor(
    private route: ActivatedRoute,
    private roadService: RoadService
  ) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.roadService.getRoadById(id).subscribe(r => {
      // Convert boolean → UP/DOWN
      r.status = r.detectorStatus ? 'UP' : 'DOWN';

      // Convert congestionLevel enum → readable word
      r.congestionLevelStr = this.mapCongestionLevel(r.congestionLevel);

      this.road = r;
    });
  }

  private mapCongestionLevel(level: string | undefined): string {
    switch (level) {
      case 'NORMAL':
        return 'Low';
      case 'HIGH':
        return 'Medium';
      case 'VERY_HIGH':
        return 'High';
      default:
        return 'Unknown';
    }
  }
}
