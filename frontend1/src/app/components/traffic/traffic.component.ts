import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TrafficService } from '../../services/traffic.service';
import { TrafficDataDto } from '../../models/traffic-data.dto';

@Component({
  selector: 'app-traffic',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './traffic.component.html',
  styleUrls: ['./traffic.component.css'],
  providers: [TrafficService]
})
export class TrafficComponent implements OnInit {

  trafficData: TrafficDataDto[] = [];

  constructor(private trafficService: TrafficService) {}

  ngOnInit(): void {
    this.loadTraffic();
  }

  loadTraffic() {
    this.trafficService.getAllTraffic().subscribe(
      data => this.trafficData = data
    );
  }

 getBadgeClass(congestion: number): string {
  if (congestion === 0) return 'badge-low';
  if (congestion === 1) return 'badge-medium';
  if (congestion === 2) return 'badge-high';
  return 'badge-low';
}
getCongestionText(level: number): string {
  switch (level) {
    case 0: return 'Low';
    case 1: return 'Medium';
    case 2: return 'High';
    default: return 'Unknown';
  }
}

}
