import 'package:flutter/material.dart';

class AmplitudeMeter extends StatelessWidget {
  static const double maxDb = 120;

  final Color dotActiveColor;
  Color dotColor;
  final Size dotSize;
  final ShapeBorder dotShape;
  final EdgeInsets dotSpacing;
  final double dbLevel;
  final int resolution; // 1 = 1 dot, 2 = 3 dots, 3 = 5 dots etc..
  final double sensitivity; //  How sensitive 

  int activeDotsRadius; // How many dots on a side should be active

  AmplitudeMeter({
    @required this.dbLevel,
    this.dotColor = Colors.grey,
    this.dotActiveColor = Colors.blue,
    this.resolution = 3,
    this.dotSize = const Size(10.0, 10.0),
    this.dotSpacing = const EdgeInsets.all(6.0),
    this.dotShape = const CircleBorder(),
    this.sensitivity = 1.0,
  }) {
    getActiveDotsRadius(dbLevel);
    this.dotColor =dotColor.withOpacity(0.5);
  }

  getActiveDotsRadius(double dbLevel) {
    // print(dbLevel);
    int radius = activeDotsRadius = (((sensitivity*dbLevel) / maxDb) * resolution).toInt();
    if(radius > resolution) {
      radius=resolution;
    }
    // print("Active Dot Radius: $activeDotsRadius");
  }

  List<Widget> _buildDots() {
    List<Widget> dots = [];
    for (int i = 0; i < resolution; i++) {
      final color = (i < activeDotsRadius) ? dotActiveColor : dotColor;
      final size = dotSize;
      final shape = dotShape;

      //  After first start adding in behind to make symmetrical
      if (i > 0) {
        dots.insert(0,
          Container(
            width: size.width,
            height: size.height,
            margin: dotSpacing,
            decoration: ShapeDecoration(color: color, shape: shape),
          ),
        );
      }

      dots.add(
        Container(
          width: size.width,
          height: size.height,
          margin: dotSpacing,
          decoration: ShapeDecoration(color: color, shape: shape),
        ),
      );
    }
    return dots;
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      child: Row(
        mainAxisSize: MainAxisSize.min,
        children: _buildDots(),
      ),
    );
  }
}
