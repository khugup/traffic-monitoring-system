export interface TrafficDataDto {
  roadName: string;
  detectorName: string;
  occupancyPercentage: number;
  averageSpeed: number;
  volume: number;
  classOneVolume: number;
  classTwoVolume: number;
  classThreeVolume: number;
  period: number;
  date: string;
  congestionLevel: number;
}
