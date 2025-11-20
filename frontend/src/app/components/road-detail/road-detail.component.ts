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

  constructor(private route: ActivatedRoute, private roadService: RoadService) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.roadService.getRoadById(id).subscribe(r => {
      // Map backend boolean to string for display
      r.status = r.detectorStatus ? 'UP' : 'DOWN';
      this.road = r;
    });
  }
}
